// Generated by view binder compiler. Do not edit!
package by.itacademy.homework6.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import by.itacademy.homework6.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTextRedactorBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button addChanges;

  @NonNull
  public final EditText redactorView;

  private ActivityTextRedactorBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button addChanges, @NonNull EditText redactorView) {
    this.rootView = rootView;
    this.addChanges = addChanges;
    this.redactorView = redactorView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTextRedactorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTextRedactorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_text_redactor, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTextRedactorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addChanges;
      Button addChanges = rootView.findViewById(id);
      if (addChanges == null) {
        break missingId;
      }

      id = R.id.redactorView;
      EditText redactorView = rootView.findViewById(id);
      if (redactorView == null) {
        break missingId;
      }

      return new ActivityTextRedactorBinding((ConstraintLayout) rootView, addChanges, redactorView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
