package by.itacademy.homework6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
            runDialog(this)
        }
        binding.settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun runDialog(context: Context) {
        val builder = MaterialAlertDialogBuilder(context)
        val constraintLayout = getEditTextLayout(context)
        //  val textInputLayout = constraintLayout.findViewWithTag<TextInputLayout>("textInputLayout")
        val textInputEditText = constraintLayout.findViewWithTag<TextInputEditText>("textInputEditText")
        builder.apply {
            setTitle("Add file name")
            setView(constraintLayout)
            setNeutralButton("CANCEL", null)
            setCancelable(false)
            setPositiveButton("SAVE", null).setOnDismissListener {
                val name = textInputEditText.text.toString()
                Toast.makeText(context, String.format("SAVE name=%s", name), Toast.LENGTH_SHORT).show()
                saveFile(name)
            }
        }
        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.isNullOrBlank()
            }
        })
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
            id = View.generateViewId()
            tag = "textInputLayout"
            addView(textInputEditText)
        }
        val constraintLayout = ConstraintLayout(context).apply {
            layoutParams = layoutParamsConstraint
            id = View.generateViewId()
            addView(textInputLayout)
        }
        //  val constraintSet = ConstraintSet().clone(constraintLayout)
        return constraintLayout
    }

    private fun saveFile(name: String) {

    }

}