/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.stripesstuff.examples.session;

import org.stripesstuff.plugin.session.Session;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

/**
 * Example of Session annotation use.
 * 
 * @author Christian Poitras
 */
public class CounterAction implements ActionBean {
    
    ActionBeanContext context;
    public ActionBeanContext getContext() {return context;}
    public void setContext(ActionBeanContext context) {this.context = context;}
    
    /**
     * Keep track of number of clicks.
     */
    @Session
    private Integer counter;
    /**
     * Number of clicks this submission will represent.
     */
    private Integer amount;
    
    
    /**
     * Set counter to 0 when it doesn't exists.
     */
    @Before
    public void popStart() {
        if (counter == null) counter = 0;
    }
    
    
    /**
     * Reset counter to 0.
     * @return Counter page.
     */
    @DefaultHandler
    public Resolution input() {
        return new ForwardResolution("/WEB-INF/pages/session/counter.jsp");
    }
    /**
     * Add 1 to counter.
     * @return Counter page.
     */
    @HandlesEvent("count")
    public Resolution count() {
        counter++;
        return new ForwardResolution("/WEB-INF/pages/session/counter.jsp");
    }
    /**
     * Add 1 to counter.
     * @return Counter page.
     */
    @HandlesEvent("countAmount")
    public Resolution countAmount() {
        counter += amount;
        return new ForwardResolution("/WEB-INF/pages/session/counter.jsp");
    }
    
    
    public Integer getCounter() {
        return counter;
    }
    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
