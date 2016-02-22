/**
 * 
 */
package controllers.composition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.With;

/**
 * @author valore
 *
 */
public class Logging {
	
	@With(LogAction.class)
	@Target({ElementType.TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Logs {
		String action() default "";
	}
	
	public static class LogAction extends Action<Logs> {
		
		private void before(Context ctx) {
			System.out.println(configuration.action());
			System.out.println("Before action invoked");
		} 

		public void after(Context ctx) { 
			System.out.println(ctx.flash().get("message"));
			System.out.println("After action invoked");
		} 
		  
		public F.Promise<Result> call(Http.Context context) throws Throwable {
			before(context); 
			Promise<Result> result = delegate.call(context);
			after(context);
			return result; 
		}
	}

}
