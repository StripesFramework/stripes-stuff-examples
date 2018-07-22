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

package org.stripesstuff.examples.waitpage;

import org.stripesstuff.plugin.waitpage.WaitPage;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

/**
 * Example of WaitPage annotation use.
 * 
 * @author Christian Poitras
 */
public class SlowAction implements ActionBean {
    
    ActionBeanContext context;
    public ActionBeanContext getContext() {return context;}
    public void setContext(ActionBeanContext context) {this.context = context;}
    
    
    /**
     * Event's progression.
     */
    private int progress;
    /**
     * True after event completes.
     */
    private boolean complete;
    
    
    /**
     * Go to index page.
     * @return index page
     */
    @DefaultHandler
    public Resolution input() {
        return new ForwardResolution("/WEB-INF/pages/waitpage/index.jsp");
    }
    /**
     * Execute a slow event.
     * @return index page
     */
    @HandlesEvent("slowEvent")
    @WaitPage(path="/WEB-INF/pages/waitpage/wait.jsp", delay=1000, refresh=1000)
    public Resolution slowEvent() {
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(1000);
                progress = i*10;
            }
        } catch (InterruptedException e) {
        }
        complete = true;
        return new ForwardResolution("/WEB-INF/pages/waitpage/index.jsp");
    }
    /**
     * Execute a slow event with an AJAX updater.
     * @return index page
     */
    @HandlesEvent("slowEventWithAjaxUpdater")
    @WaitPage(path="/WEB-INF/pages/waitpage/ajaxwait.jsp", delay=1000, refresh=1000, ajax="/WEB-INF/pages/waitpage/ajax.jsp")
    public Resolution slowEventWithAjaxUpdater() {
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(1000);
                progress = i*10;
            }
        } catch (InterruptedException e) {
        }
        complete = true;
        return new ForwardResolution("/WEB-INF/pages/waitpage/index.jsp");
    }
    
    
    public int getProgress() {
        return progress;
    }
    public boolean isComplete() {
        return complete;
    }
}
