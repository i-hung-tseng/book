package viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


//
class ViewModelFactory  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TitleViewModel::class.java)) {
            return TitleViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}