package com.gmail.cwramirezg.task.features.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.cwramirezg.task.R;
import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.features.main.MainNavigationActivity;
import com.gmail.cwramirezg.task.features.shared.BaseFragment;
import com.gmail.cwramirezg.task.utils.SimpleDividerItemDecoration;
import com.gmail.cwramirezg.task.utils.UtilMethods;
import com.gmail.cwramirezg.task.utils.dialogs.CustomDialog;
import com.gmail.cwramirezg.task.utils.dialogs.ProgressDialog;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TaskFragment extends BaseFragment implements TaskFragmentContract.View, TaskAdapter.ISelection {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    @Inject
    TaskFragmentPresenter presenter;
    Unbinder unbinder;

    public TaskFragment() {
    }

    public static TaskFragment newInstance() {
        return new TaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void setupViews(View view) {
        presenter.showTasks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachView();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        ProgressDialog.dismiss();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);
        rvData.addItemDecoration(new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_white));

        TaskAdapter adapter = new TaskAdapter(tasks, this);
        rvData.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
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

    @Override
    public void onClick(Task task) {
        if ("1".equalsIgnoreCase(task.getStatus())) {
            showMessage(getString(R.string.task_complete));
        } else {
            new CustomDialog.Builder(getContext())
                    .setMessage(getString(R.string.task_success))
                    .setTheme(R.style.AppTheme_Dialog)
                    .setIcon(R.drawable.ic_close)
                    .setPositiveButtonLabel(getString(R.string.label_yes))
                    .setNegativeButtonLabel(getString(R.string.label_no))
                    .setPositiveButtonlistener(() -> presenter.completeTask(task))
                    .build().show();
        }
    }

    @Override
    public void onClickLong(Task task) {
        new CustomDialog.Builder(getContext())
                .setMessage(getString(R.string.task_delete))
                .setTheme(R.style.AppTheme_Dialog)
                .setIcon(R.drawable.ic_close)
                .setPositiveButtonLabel(getString(R.string.label_yes))
                .setNegativeButtonLabel(getString(R.string.label_no))
                .setPositiveButtonlistener(() -> presenter.deleteTask(task))
                .build().show();
    }

    @OnClick(R.id.fa_add)
    public void onViewClicked() {
        ((MainNavigationActivity) Objects.requireNonNull(getActivity())).navigateTo(R.id.nav_add);
    }
}