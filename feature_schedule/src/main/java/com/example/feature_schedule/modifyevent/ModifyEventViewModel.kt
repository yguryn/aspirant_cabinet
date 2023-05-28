package com.example.feature_schedule.modifyevent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.eventusecases.DeleteEventUseCase
import com.example.core.eventusecases.GetEventByIdUseCase
import com.example.core.eventusecases.ModifyEventUseCase
import com.example.core.model.Event
import com.example.core.utils.SharedPreferencesHelper
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModifyEventViewModel @Inject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val modifyEventUseCase: ModifyEventUseCase,
    private val preferencesHelper: SharedPreferencesHelper,
    private val deleteEventUseCase: DeleteEventUseCase
) :
    ViewModel() {

    private var _event = MutableLiveData<Event>()
    val event: LiveData<Event>
        get() = _event

    fun getEventById(id: String) {
        getEventByIdUseCase.execute(id) {
            Log.d("TTT", "OPA $it")
            _event.value = it
        }
    }

    fun modifyEvent(event: Event) {
        modifyEventUseCase.execute(event)
    }

    fun deleteEvent(eventId: String) {
        deleteEventUseCase.execute(eventId)
    }

    fun getUserId() = preferencesHelper.getString("USER_ID")
}