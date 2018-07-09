package com.telnet.jukebox.webservice.test.mocking.business.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.telnet.jukebox.webservice.resources.ZanrResource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@ApplicationPath("rs")
public class ApplicationConfig extends Application {

    // ======================================
    // =             Attributes             =
    // ======================================

    private final Set<Class<?>> classes;

    // ======================================
    // =            Constructors            =
    // ======================================

    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(ZanrResource.class);
        classes = Collections.unmodifiableSet(c);
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
