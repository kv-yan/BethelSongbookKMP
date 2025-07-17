package am.bethel.application.share.data.usecase

import am.bethel.application.common.domain.model.Song
import am.bethel.application.share.domain.repository.ShareHelper
import am.bethel.application.share.domain.usecase.ShareSongUseCase

class ShareSongUseCaseImpl(
    private val shareHelper: ShareHelper
) : ShareSongUseCase {
    override suspend fun invoke(song: Song) {
        shareHelper.shareText(song.songWords)
    }
}