package com.gmail.cwramirezg.task.features.sync;

import com.gmail.cwramirezg.task.data.PreferenceManager;
import com.gmail.cwramirezg.task.data.pojos.Configuracion;
import com.gmail.cwramirezg.task.data.source.DataSourceRepository;
import com.gmail.cwramirezg.task.features.shared.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


class SyncPresenter extends BasePresenter<SyncContract.View> implements SyncContract.Presenter {

    private final DataSourceRepository dataSource;
    private final PreferenceManager preferenceManager;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private int doneItems;
    private int totalItems;
    private Configuracion config;

    @Inject
    public SyncPresenter(DataSourceRepository dataSource, PreferenceManager preferenceManager) {
        this.dataSource = dataSource;
        this.preferenceManager = preferenceManager;
        this.config = preferenceManager.getConfig();
    }

    @Override
    public void attachView(SyncContract.View mvpView) {
        dataSource.setOnlineMode(true);
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        dataSource.setOnlineMode(!config.isBatch());
        disposables.clear();
    }

    @Override
    public void setupView() {
        boolean isBatch = preferenceManager.getConfig().isBatch();
        String title, message;
        if (!isBatch) {
            title = "Descarga";
            message = "¿Desea realizar la descarga de información?";
        } else {
            title = "Carga";
//            TODO: Validar con Kathy
            message = "¿Desea cargar lo trabajado?";
        }

        getView().setupDialog(title, message, !isBatch);
    }

    @Override
    public void startSync() {
        boolean isBatch = preferenceManager.getConfig().isBatch();
        getView().startSync();
        if (!isBatch) {
            startDownload();
        } else {
//            startUpload();
        }
    }

    private void startDownload() {


    }

    @Override
    public void syncSuccess() {
        config.setBatch(!config.isBatch());
        preferenceManager.saveConfig(config);

        dataSource.setOnlineMode(!config.isBatch());
        getView().syncSuccess();
    }

}