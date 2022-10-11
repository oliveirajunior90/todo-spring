package com.example.demo.controller

import com.example.demo.task.fixtures.TaskResourceFixture
import spock.lang.Specification

class TaskControllerITSPEC extends Specification{

    TaskController taskController;

    def "should initialize controller" () {

        TaskResourceFixture task = new TaskResourceFixture();

        print(task.build())


        given:
        taskController.search()
        expect: "been creation is successfull"
        taskController != null

    }
}
