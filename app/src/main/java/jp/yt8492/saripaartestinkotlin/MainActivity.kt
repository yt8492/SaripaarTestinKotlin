package jp.yt8492.saripaartestinkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import jp.yt8492.saripaartestinkotlin.NotBlank

import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.Length
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import com.mobsandgeeks.saripaar.annotation.Pattern

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() ,Validator.ValidationListener{

    @NotEmpty
    lateinit var editId: EditText

    @NotEmpty
    @Password
    lateinit var editPass1: EditText

    @NotEmpty
    @ConfirmPassword
    lateinit var editPass2: EditText

    lateinit var validator: Validator

    override fun onValidationSucceeded() {
        Log.d("debug","Success")
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        errors?.let {
            for(error: ValidationError in it){
                val view = error.view
                if(view is EditText){
                    view.error = error.getCollatedErrorMessage(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        editId = edit_id
        editPass1 = edit_pass
        editPass2 = edit_pass_confirm

        validator = Validator(this)
        validator.put(editId, NotBlank())
        validator.put(editPass1, NotBlank())
        validator.put(editPass2, NotBlank())
        validator.setValidationListener(this)

        button.setOnClickListener {
            validator.validate()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
