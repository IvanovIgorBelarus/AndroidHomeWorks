package by.itacademy.homework6

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import by.itacademy.homework6.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addFile.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this)
            val constraintLayout = getEditTextLayout(this)
            val textInputLayout=constraintLayout.findViewWithTag<TextInputLayout>("textInputLayout")
            val textInputEditText=constraintLayout.findViewWithTag<TextInputEditText>("textInputEditText")
            builder.apply {
                setTitle("Add file name")
                setView(constraintLayout)
                setNeutralButton("CANCEL", null)
                setPositiveButton("SAVE",null)
                setCancelable(false)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun getEditTextLayout(context: Context): ConstraintLayout {
        val layoutParamsConstraint = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        val textInputEditText = TextInputEditText(context).apply {
            id = View.generateViewId()
            tag = "textInputEditText"
        }
        val textInputLayout = TextInputLayout(context).apply {
            boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
            hint = "Name"
            id = View.generateViewId()
            tag = "textInputLayout"
            addView(textInputEditText)
        }
        val constraintLayout = ConstraintLayout(context).apply {
            layoutParams = layoutParamsConstraint
            id = View.generateViewId()
            addView(textInputLayout)
        }
        val constraintSet = ConstraintSet().clone(constraintLayout)
        return constraintLayout
    }
}