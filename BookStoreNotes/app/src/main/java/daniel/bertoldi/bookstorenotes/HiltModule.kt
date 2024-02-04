package daniel.bertoldi.bookstorenotes

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import daniel.bertoldi.bookstorenotes.model.api.BookApiRepo
import daniel.bertoldi.bookstorenotes.model.api.BookService

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideApiRepo() = BookApiRepo(BookService.bookApi)
}