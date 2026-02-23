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

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 * Simple readiness health check.
 * Returns UP status to indicate the application is ready to accept traffic.
 */
@Readiness
@ApplicationScoped
public class DatabaseReadinessCheck implements HealthCheck {
	
	@Override
	public HealthCheckResponse call() {
		// Simple health check - always returns UP
		return HealthCheckResponse.named("DatabaseReadinessCheck")
				.withData("status", "UP")
				.up()
				.build();
	}
}