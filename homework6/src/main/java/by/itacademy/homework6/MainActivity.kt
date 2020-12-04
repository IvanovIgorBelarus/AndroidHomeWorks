package by.itacademy.homework6

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.homework6.Data.Companion.dataInstance
import by.itacademy.homework6.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), FileActionListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fileAdapter: FileAdapter
    private val fileOperations: FileOperations = FileOperationsImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            addFile.setOnClickListener {
                runDialog(this@MainActivity)
            }
            settings.setOnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }
        }
        setRecycler(dataInstance.fileList, this)
    }

    override fun onStart() {
        super.onStart()
        dataInstance.fileList.clear()
        fileOperations.getFiles()
        fileAdapter.notifyDataSetChanged()
    }

    private fun runDialog(context: Context) {
        val builder = MaterialAlertDialogBuilder(context)
        val constraintLayout = getEditTextLayout(context)
        val textInputEditText = constraintLayout.findViewWithTag<TextInputEditText>("textInputEditText")
        builder.apply {
            setTitle("Add file name")
            setView(constraintLayout)
            setNegativeButton("CANCEL", null)
            setCancelable(false)
            setPositiveButton("SAVE") { _: DialogInterface, _: Int ->
                val name = textInputEditText.text.toString()
                Toast.makeText(context, String.format("SAVE File  %s", name), Toast.LENGTH_SHORT).show()
                fileOperations.saveFile(name)
                fileAdapter.notifyDataSetChanged()
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
        return ConstraintLayout(context).apply {
            layoutParams = layoutParamsConstraint
            id = View.generateViewId()
            addView(textInputLayout)
        }
    }

    private fun setRecycler(fileList: List<String>, activity: MainActivity) {
        val recycler = binding.recycler
        fileAdapter = FileAdapter(fileList, activity)
        recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = fileAdapter
        }
    }

    override fun onFileClicked(position: Int) {
        intent = Intent(this, TextRedactorActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

}