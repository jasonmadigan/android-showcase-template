package com.aerogear.androidshowcase.di;

import android.content.Context;

import com.aerogear.androidshowcase.AesCryptoTest;
import com.aerogear.androidshowcase.RsaCryptoTest;
import com.aerogear.androidshowcase.StorageFeatureTest;
import com.aerogear.androidshowcase.domain.repositories.NoteRepository;
import com.aerogear.androidshowcase.domain.store.NoteDataStoreFactory;
import com.aerogear.androidshowcase.domain.store.SecureFileNoteStoreTest;
import com.aerogear.androidshowcase.domain.store.sqlite.SqliteNoteStoreTest;
import com.aerogear.androidshowcase.features.authentication.providers.OpenIDAuthenticationProvider;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Define the DI graph for Dagger for the tests. You will need to add new methods into this interface if new tests are added and then need DI.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, SecureApplicationTestModule.class, MainActivityModule.class})
public interface SecureApplicationTestComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        SecureApplicationTestComponent.Builder application(SecureTestApplication application);

        SecureApplicationTestComponent build();
    }

    void inject(SecureTestApplication app);

    //when a new test is added, if it needs DI, please add a new "inject" method here. The argument should be an instance of the test.
    //Then in the test itself, make sure call this method during setup. See StorageFeatureTest for example.
    void inject(StorageFeatureTest fragmentTest);
    void inject(AesCryptoTest cryptoTest);
    void inject(RsaCryptoTest rsaTest);
    void inject(SqliteNoteStoreTest noteStoreTest);
    void inject(SecureFileNoteStoreTest fileNoteStoreTest);

    Context context();
    NoteDataStoreFactory provideNoteDataStoreFactory();
    NoteRepository noteRepository();
    OpenIDAuthenticationProvider authProvider();
}
