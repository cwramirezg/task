package com.gmail.cwramirezg.task.features.task.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.cwramirezg.task.R;
import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.features.shared.BaseFragment;
import com.gmail.cwramirezg.task.utils.UtilMethods;
import com.gmail.cwramirezg.task.utils.dialogs.CustomDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddTaskFragment extends BaseFragment implements AddTaskFragmentContract.View {

    @BindView(R.id.til_descripcion)
    TextInputLayout tilDescripcion;

    @Inject
    AddTaskFragmentPresenter presenter;
    Unbinder unbinder;

    public AddTaskFragment() {
    }

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getActivityComponent().inject(this);
        presenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle avedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @OnClick(R.id.bt_add)
    public void onViewClicked() {

        String value = tilDescripcion.getEditText().getText().toString();
        if (TextUtils.isEmpty(value.trim())) {
            new CustomDialog.Builder(getContext())
                    .setTitle(getString(R.string.alert))
                    .setMessage(getString(R.string.add_value))
                    .setPositiveButtonLabel(getString(R.string.label_ok))
                    .setIcon(R.drawable.ic_alert)
                    .setTheme(R.style.AppTheme_Dialog_Warning)
                    .setPositiveButtonlistener(super::onBackPressed)
                    .build().show();
            return;
        }
        presenter.addTask(new Task(value));
    }

    @Override
    public void showMessage(String message) {
        tilDescripcion.getEditText().setText("");
        UtilMethods.showToast(message);
    }

    @Override
    public void showError(String message) {
        new CustomDialog.Builder(getContext())
                .setMessage(message)
                .setTheme(R.style.AppTheme_Dialog_Error)
                .setIcon(R.drawable.ic_close)
                .setPositiveButtonLabel(getString(R.string.label_ok))
                .build().show();
    }
}