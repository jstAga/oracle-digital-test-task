package com.example.movieapp.core.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel> : BottomSheetDialogFragment() {
  
  private var _navController: NavController? = null
  private var _binding: Binding? = null
  
  protected open val binding: Binding
    get() = _binding!!
  
  protected abstract fun inflateViewBinding(): Binding
  protected abstract val viewModel: ViewModel
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = inflateViewBinding()
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (isAdded && activity != null) {
      initView()
      initRequest()
      initListeners()
      initSubscribers()
    }
  }
  
  fun navigate(directions: NavDirections) {
    _navController?.navigate(directions)
  }
  
  fun navigate(destinationId: Int) {
    _navController?.navigate(destinationId)
  }
  
  fun navigateBack() {
    _navController?.popBackStack()
  }
  
  fun navigateClearStack(destinationId: Int) {
    val navBuilder = NavOptions.Builder()
    val navOptions: NavOptions = navBuilder.setPopUpTo(_navController?.currentDestination?.id ?: 0, true).build()
    _navController?.navigate(destinationId, null, navOptions)
  }
  
  fun navigateSafely(directions: NavDirections) {
    _navController?.currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
  }
  
  protected open fun initView() {}
  protected open fun initRequest() {}
  protected open fun initListeners() {}
  protected open fun initSubscribers() {}
  
  fun safeFlowGather(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    gather: suspend () -> Unit,
  ) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
        gather()
      }
    }
  }
  
  protected fun <T : Any> Flow<PagingData<T>>.collectPaging(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: PagingData<T>) -> Unit,
  ) {
    safeFlowGather(lifecycleState) { this.collectLatest { action(it) } }
  }
}