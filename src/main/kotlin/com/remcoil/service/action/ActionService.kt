package com.remcoil.service.action

import com.remcoil.dao.action.ActionDao
import com.remcoil.data.model.action.Action
//import com.remcoil.data.model.action.ActionIdentity
import com.remcoil.data.model.action.FullAction

class ActionService(private val dao: ActionDao) {

    fun getAll(): List<Action> {
        return dao.getAll()
    }

    fun getByTaskId(taskId: Int): List<FullAction> {
        return dao.getByTaskId(taskId)
    }

    fun updateAction(action: Action) {
        return dao.updateAction(action)
    }

    fun createAction(action: Action): Action? {
        return if (dao.checkAction(action)) null else dao.createAction(action)
    }

    fun deleteAction(actionId: Int) {
        dao.deleteAction(actionId)
    }
}