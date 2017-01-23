/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.joketeller.application.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.joke.JokeTeller;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeTellerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.application.joketeller.udacity.com",
                ownerName = "backend.application.joketeller.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that tells a joke
     */
    @ApiMethod(name = "tellJoke")
    public JokeOutputBean tellJoke() {
        JokeTeller jokeTeller = new JokeTeller();
        JokeOutputBean response = new JokeOutputBean();
        response.setData(jokeTeller.getJoke());
        return response;
    }
}
