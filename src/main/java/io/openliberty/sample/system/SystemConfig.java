/*******************************************************************************
 * Copyright (c) 2017, 2020 IBM Corporation and others.
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
import jakarta.inject.Provider;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SystemConfig {

  private volatile boolean initialized = false;

    @PostConstruct
    void init() {
        // Simulate slow startup that takes 7 minutes
        // This will cause startup probe to fail (exceeds 498s timeout)
        new Thread(() -> {
            try {
                // 7 minutes = 420 seconds delay
                // Simulates slow startup (e.g. DB warmup, cache initialization)
                Thread.sleep(420_000);
                initialized = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public boolean isInitialized() {
        return initialized;
    }

  @Inject
  @ConfigProperty(name = "io_openliberty_sample_system_inMaintenance")
  Provider<Boolean> inMaintenance;


  public boolean isInMaintenance() {
    return inMaintenance.get();
  }
}
