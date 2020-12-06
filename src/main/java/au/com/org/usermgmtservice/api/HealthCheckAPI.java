/**
 * 
 */
package au.com.org.usermgmtservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sekhar
 *
 */

@RestController
public class HealthCheckAPI {

	//Endpoint to check if the API is up and running
	@RequestMapping(method = RequestMethod.GET, value="/health")
	public HttpStatus checkHealth() { return HttpStatus.OK; }
	
}
