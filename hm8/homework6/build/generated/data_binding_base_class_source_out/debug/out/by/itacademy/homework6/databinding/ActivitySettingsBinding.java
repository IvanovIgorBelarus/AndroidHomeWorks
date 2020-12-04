// Generated by view binder compiler. Do not edit!
package by.itacademy.homework6.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import by.itacademy.homework6.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RadioButton externalStorage;

  @NonNull
  public final RadioButton internalStorage;

  @NonNull
  public final RadioGroup radioGroup;

  @NonNull
  public final Button update;

  private ActivitySettingsBinding(@NonNull ConstraintLayout rootView,
      @NonNull RadioButton externalStorage, @NonNull RadioButton internalStorage,
      @NonNull RadioGroup radioGroup, @NonNull Button update) {
    this.rootView = rootView;
    this.externalStorage = externalStorage;
    this.internalStorage = internalStorage;
    this.radioGroup = radioGroup;
    this.update = update;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.externalStorage;
      RadioButton externalStorage = rootView.findViewById(id);
      if (externalStorage == null) {
        break missingId;
      }

      id = R.id.internalStorage;
      RadioButton internalStorage = rootView.findViewById(id);
      if (internalStorage == null) {
        break missingId;
      }

      id = R.id.radioGroup;
      RadioGroup radioGroup = rootView.findViewById(id);
      if (radioGroup == null) {
        break missingId;
      }

      id = R.id.update;
      Button update = rootView.findViewById(id);
      if (update == null) {
        break missingId;
      }

      return new ActivitySettingsBinding((ConstraintLayout) rootView, externalStorage,
          internalStorage, radioGroup, update);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
