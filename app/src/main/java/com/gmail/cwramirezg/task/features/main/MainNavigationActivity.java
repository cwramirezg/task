package com.gmail.cwramirezg.task.features.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gmail.cwramirezg.task.R;
import com.gmail.cwramirezg.task.data.PreferenceManager;
import com.gmail.cwramirezg.task.features.shared.BaseActivity;
import com.gmail.cwramirezg.task.features.shared.BaseFragment;
import com.gmail.cwramirezg.task.features.task.TaskFragment;
import com.gmail.cwramirezg.task.features.task.add.AddTaskFragment;
import com.gmail.cwramirezg.task.utils.UtilMethods;
import com.gmail.cwramirezg.task.utils.dialogs.CustomDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainNavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Inject
    PreferenceManager preferenceManager;

    private BaseFragment fragment;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainNavigationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        lyProgress = findViewById(R.id.ly_progress);

        setupNavigation();
    }

    private void setupNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        setupHeaderView();
        setupNavigationMenuView();
        navView.setNavigationItemSelectedListener(this);

    }

    private void setupHeaderView() {
        View header = navView.inflateHeaderView(R.layout.nav_header_main);
        TextView tvName = header.findViewById(R.id.tv_name);
        TextView tvUser = header.findViewById(R.id.tv_user);

        tvName.setText("Carlos Ramirez Guerra");
        tvUser.setText("cwramirezg");
    }

    public void setupNavigationMenuView() {
        navView.getMenu().clear();
        navView.inflateMenu(R.menu.drawer_task);
        MenuItem firstItem = navView.getMenu().getItem(0).getSubMenu().getItem(0);
        onNavigationItemSelected(firstItem);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (fragment != null && fragment.onBackPressed()) {
                new CustomDialog.Builder(getContext())
                        .setTitle("Alerta")
                        .setMessage("¿Está seguro que desea salir?")
                        .setPositiveButtonLabel(getString(R.string.label_yes))
                        .setIcon(R.drawable.ic_logout)
                        .setNegativeButtonLabel(getString(R.string.label_no))
                        .setPositiveButtonlistener(super::onBackPressed)
                        .build().show();
            }
        }
    }

    private void clearDrawerSelection(Menu menu) {
        int i = 0;
        while (menu != null) {
            try {
                MenuItem menuItem = menu.getItem(i++);
                menuItem.setChecked(false);
                clearDrawerSelection(menuItem.getSubMenu());
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    public void navigateTo(@IdRes int menuId) {
        MenuItem menuItem = retrieveMenuItem(navView.getMenu(), menuId);
        onNavigationItemSelected(menuItem);
    }

    private MenuItem retrieveMenuItem(Menu menu, @IdRes int menuId) {
        MenuItem menuItem = null;
        int i = 0;
        while (menu != null) {
            try {
                MenuItem m = menu.getItem(i++);
                if (m.getItemId() == menuId) {
                    return m;
                }
                m = retrieveMenuItem(m.getSubMenu(), menuId);
                if (m != null) {
                    return m;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return menuItem;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (getSupportActionBar() == null) return false;

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_list:
                getSupportActionBar().setTitle("Lista de tareas");
                fragment = TaskFragment.newInstance();
                replaceFragment(fragment, R.id.fl_main);
                break;
            case R.id.nav_add:
                getSupportActionBar().setTitle("Agregar tarea");
                fragment = AddTaskFragment.newInstance();
                replaceFragment(fragment, R.id.fl_main);
                break;
            case R.id.nav_report:
                UtilMethods.showToast("En construcción");
                break;
            case R.id.nav_settings:
                UtilMethods.showToast("En construcción");
                break;
            case R.id.nav_logout:
                openLogoutDialog();
                break;
        }

        clearDrawerSelection(navView.getMenu());
        item.setChecked(true);

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openLogoutDialog() {
        new CustomDialog.Builder(MainNavigationActivity.this)
                .setTitle(getString(R.string.dialog_logout_title))
                .setMessage(getString(R.string.dialog_logout_message))
                .setPositiveButtonLabel(getString(R.string.label_yes))
                .setNegativeButtonLabel(getString(R.string.label_no))
                .setPositiveButtonlistener(this::finish)
                .build().show();
    }

}
