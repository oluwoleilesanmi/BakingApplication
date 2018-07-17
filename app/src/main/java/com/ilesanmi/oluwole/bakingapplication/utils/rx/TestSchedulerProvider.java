package com.ilesanmi.oluwole.bakingapplication.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public class TestSchedulerProvider implements SchedulerProvider {
    private TestScheduler testScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler){
        this.testScheduler = testScheduler;
    }
    @Override
    public Scheduler ui() {
        return testScheduler;
    }

    @Override
    public Scheduler computation() {
        return testScheduler;
    }

    @Override
    public Scheduler io() {
        return testScheduler;
    }
}
