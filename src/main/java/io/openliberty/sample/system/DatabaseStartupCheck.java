/*******************************************************************************
 * Copyright (c) 2018, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
package io.openliberty.sample.system;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Startup;

/**
 * Startup health check that waits for application initialization.
 * Returns DOWN until the 7-minute initialization completes.
 */
@Startup
@ApplicationScoped
public class DatabaseStartupCheck implements HealthCheck {
	
	@Inject
	SystemConfig systemConfig;
	
	@Override
	public HealthCheckResponse call() {
		// Check if initialization is complete
		if (!systemConfig.isInitialized()) {
			return HealthCheckResponse.named("DatabaseStartupCheck")
					.withData("status", "DOWN")
					.withData("reason", "Application initialization in progress")
					.down()
					.build();
		}
		
		return HealthCheckResponse.named("DatabaseStartupCheck")
				.withData("status", "UP")
				.up()
				.build();
	}
}