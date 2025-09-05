package com.gpartners.agriscanmobileapplication.ui.mwanedu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpartners.agriscanmobileapplication.databinding.LayoutChatHistoryBinding
import com.gpartners.agriscanmobileapplication.databinding.LayoutChatHistoryEmptyBinding

class ChatHistoryFragment : Fragment() {

    private val viewModel: MwaneduViewModel by activityViewModels()

    private var _historyBinding: LayoutChatHistoryBinding? = null
    private var _emptyBinding: LayoutChatHistoryEmptyBinding? = null

    private val historyBinding get() = _historyBinding!!
    private val emptyBinding get() = _emptyBinding!!

    private lateinit var historyAdapter: ChatHistoryAdapter

    // Root view reference so we can switch between them
    private var currentRoot: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate both bindings once
        _historyBinding = LayoutChatHistoryBinding.inflate(inflater, container, false)
        _emptyBinding = LayoutChatHistoryEmptyBinding.inflate(inflater, container, false)

        historyAdapter = ChatHistoryAdapter()
        historyBinding.recyclerChatHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }

        // Default root = empty state
        currentRoot = emptyBinding.root
        return currentRoot!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.chatHistory.observe(viewLifecycleOwner, Observer { list ->
            if (list.isNullOrEmpty()) {
                // Switch to empty layout
                if (currentRoot !== emptyBinding.root) {
                    switchRoot(emptyBinding.root)
                }
            } else {
                // Switch to history layout
                if (currentRoot !== historyBinding.root) {
                    switchRoot(historyBinding.root)
                }
                historyAdapter.submitList(list.toList())
            }
        })
    }

    private fun switchRoot(newRoot: View) {
        val parent = currentRoot?.parent as? ViewGroup
        val index = parent?.indexOfChild(currentRoot)
        parent?.removeView(currentRoot)
        if (index != null && index >= 0) {
            parent.addView(newRoot, index)
        } else {
            parent?.addView(newRoot)
        }
        currentRoot = newRoot
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _historyBinding = null
        _emptyBinding = null
    }
}
