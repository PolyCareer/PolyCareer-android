package com.example.polycareer.screens.quiz.quiz_item

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.polycareer.App
import com.example.polycareer.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class QuizItemFragment : Fragment(), View.OnClickListener {
    private lateinit var rgAnswers: RadioGroup
    private lateinit var btnNext: AppCompatButton
    private lateinit var progressBar: ProgressBar

        private val viewModel: QuizItemViewModel by viewModel()

    private var pressedTime = -1L

    private val stateObserver = Observer<QuizItemViewModel.QuizItemState> { state ->
        if (state.toResults != -1L) toResults(state.toResults)
        if (state.errorMessage.isEmpty()) {
            bindAnswers(state.answers)
        } else {
            showToast(state.errorMessage)
        }
    }

    private fun bindAnswers(answers: List<String>) {
        LayoutInflater.from(context)
        rgAnswers.removeAllViews()
        for (answer in answers) {
            val newRadioButton = AppCompatRadioButton(context)
            newRadioButton.setStyle()

            newRadioButton.id = View.generateViewId()
            newRadioButton.text = answer
            rgAnswers.addView(newRadioButton)
        }
        progressBar.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__main__quiz_item, container, false)
        rgAnswers = rootView.findViewById(R.id.fragment__main__quiz_item__answers_rg)
        btnNext = rootView.findViewById(R.id.fragment__main__quiz_item__next_btn)
        progressBar = rootView.findViewById(R.id.fragment__main__quiz_item__progress)

        viewModel.stateLiveData.observe(viewLifecycleOwner, stateObserver)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    viewModel.clearUsersAttempt()
                    findNavController().navigateUp()
                } else {
                    showToast(getString(R.string.press_again))
                }
                pressedTime = System.currentTimeMillis()
            }
        })

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener(this)

        viewModel.onFragmentCreated()
    }

    override fun onClick(v: View?) {
        val radioButtonID: Int = rgAnswers.checkedRadioButtonId
        val radioButton: View? = rgAnswers.findViewById(radioButtonID)
        if (radioButton != null) {
            val selectedAnswer = rgAnswers.indexOfChild(radioButton)

            viewModel.onNextButtonClicked(selectedAnswer)
        }
    }

    private fun toResults(tryNumber: Long) {
        val bundle = Bundle()
        bundle.putLong(App.TRY_NUMBER, tryNumber)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.action_quizItemFragment_to_quizResultsFragment, bundle)
        viewModel.navigationComplete()
    }

    private fun showToast(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

private fun AppCompatRadioButton.setStyle() {
    val dp = context.resources.displayMetrics.density.toInt()
    val sp = context.resources.displayMetrics.scaledDensity
    setTextColor(ContextCompat.getColor(context, R.color.black))
    typeface = ResourcesCompat.getFont(context, R.font.opensans_regular)
    setTextSize(TypedValue.COMPLEX_UNIT_SP, 8 * sp)
    setPadding(5 * dp, 5 * dp, 5 * dp, 5 * dp)
    setBackgroundColor(ContextCompat.getColor(context, R.color.splash_light))

    val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    lp.gravity = Gravity.CENTER
    lp.setMargins(25 * dp, 10 * dp, 30 * dp, 10 * dp)
    layoutParams = lp
    buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.btn_light_green))
}
