package com.gpartners.agriscanmobileapplication.ui.mwanedu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpartners.agriscanmobileapplication.databinding.LayoutChatActiveBinding
import com.gpartners.agriscanmobileapplication.databinding.LayoutChatStartBinding
import com.gpartners.agriscanmobileapplication.ui.mwanedu.MwaneduViewModel

class CurrentChatFragment : Fragment() {

    private val viewModel: MwaneduViewModel by activityViewModels()

    private var _startBinding: LayoutChatStartBinding? = null
    private var _activeBinding: LayoutChatActiveBinding? = null

    private val startBinding get() = _startBinding!!
    private val activeBinding get() = _activeBinding!!

    private lateinit var chatAdapter: ChatMessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (viewModel.isChatActive.value == true) {
            _activeBinding = LayoutChatActiveBinding.inflate(inflater, container, false)
            activeBinding.root
        } else {
            _startBinding = LayoutChatStartBinding.inflate(inflater, container, false)
            startBinding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isChatActive.value == true) {
            chatAdapter = ChatMessageAdapter()
            activeBinding.recyclerChatMessages.apply {   // ✅ matches XML
                layoutManager = LinearLayoutManager(requireContext())
                adapter = chatAdapter
            }

            // Observe messages
            viewModel.messages.observe(viewLifecycleOwner, Observer {
                chatAdapter.submitList(it.toList())
                activeBinding.recyclerChatMessages.scrollToPosition(it.size - 1)
            })

            // Send button listener
            activeBinding.btnSendActive.setOnClickListener {   // ✅ matches XML
                val text = activeBinding.editPromptActive.text.toString()   // ✅ matches XML
                if (text.isNotBlank()) {
                    viewModel.sendUserMessage(text)
                    activeBinding.editPromptActive.text?.clear()
                }
            }
        } else {
            startBinding.btnStartNewChat.setOnClickListener {   // ✅ matches XML
                viewModel.startChat()
                parentFragmentManager.beginTransaction()
                    .replace(id, CurrentChatFragment())
                    .commit()
            }

            }
        }
    }

