package com.example.todoapp.todo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.todo.model.Todo
import com.example.todoapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_todo.*
import javax.inject.Inject

@AndroidEntryPoint
class TodoFragment : Fragment(), TodoContract.View {

    companion object {
        private const val TAG = "TodoFragment"
        fun getInstance(): TodoFragment {
            return TodoFragment()
        }
    }

    @Inject
    lateinit var presenter: TodoPresenter

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_todo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onAttachView(this)
        initRecyclerView()
        presenter.onViewCreated()

        swipeContainer.setOnRefreshListener {
            presenter.onViewCreated()
            swipeContainer.isRefreshing = false
        }
    }

    private fun initRecyclerView() {
        todoAdapter = TodoAdapter(
            requireActivity(),
            mutableListOf(),
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = todoAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun showTodo(todo: List<Todo>) {
        todoAdapter.update(todo)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}