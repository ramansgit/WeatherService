package com.weather.app;

import java.net.UnknownHostException;

import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weather.api.WeatherApi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * holds dropwizard configuration
 * 
 * @author ramans
 *
 */
public class WeatherApp extends Application<WeatherAppConfig> {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApp.class);
	public static void main(String[] args) throws Exception {	
		new WeatherApp().run(args);
	
	}

	/**
	 * initalize the swagger configuration
	 */
	@Override
	public void initialize(Bootstrap<WeatherAppConfig> bootstrap) {

		bootstrap.addBundle(new SwaggerBundle<WeatherAppConfig>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WeatherAppConfig configuration) {
				// this would be the preferred way to set up swagger, you can
				// also construct the object here programtically if you want
				return configuration.swaggerBundleConfiguration;
			}
		});
	}

	/**
	 * run method cvcvc
	 */
	@Override
	public void run(WeatherAppConfig config, Environment env) throws UnknownHostException {
		env.jersey().register(new WeatherApi());
		env.servlets().setSessionHandler(new SessionHandler());
		final WeatherAppHealthCheck healthCheck = new WeatherAppHealthCheck(config.getVersion());
		env.healthChecks().register("template", healthCheck);
		env.jersey().register(healthCheck);

	}
}