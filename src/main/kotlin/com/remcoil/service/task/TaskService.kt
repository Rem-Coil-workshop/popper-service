package com.remcoil.service.task

import com.remcoil.dao.task.TaskDao
import com.remcoil.data.model.task.Task
import com.remcoil.data.model.task.TaskIdentity

class TaskService(private val taskDao: TaskDao) {

    fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    fun deleteTask(task_name: String) {
        taskDao.deleteTask(task_name)
    }

    fun createTask(taskIdentity: TaskIdentity): Task {
        val task = Task(taskIdentity)
        return taskDao.createTask(task)
    }
}