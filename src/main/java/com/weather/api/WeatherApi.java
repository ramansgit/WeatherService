package com.weather.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.weather.model.ApiResponseMessage;
import com.weather.model.WeatherResp;
import com.weather.service.WeatherService;
import com.weather.service.WeatherServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/api/v1/weather")
@Api(description = "Weather Api")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-10T14:43:58.709Z")

public class WeatherApi {

	private final WeatherService delegate = new WeatherServiceImpl();

	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "get weather deta by country", notes = "get weather data by country", response = ApiResponseMessage.class, tags = {
			"weather" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "", response = ApiResponseMessage.class) })
	public Response getWeatherData(@ApiParam(value = "country") @QueryParam("country") String country,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		WeatherResp data = delegate.getWeatherDetailsByCountry(country);
		return Response.status(Status.OK).entity(new ApiResponseMessage(data)).build();
	}

}
