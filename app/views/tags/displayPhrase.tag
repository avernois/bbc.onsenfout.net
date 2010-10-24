<div class="post ${_as == 'teaser' ? 'teaser' : ''}">
    <div class="post-metadata">
        <span class="post-author">by ${_post.author}</span>,
        <span class="post-date">${_post.postedAt.format('dd MMM yy')}</span>
    </div>
    #{if _as != 'teaser'}
        <div class="post-content">
            <div class="about">Detail: </div>
            ${_post.phrase.nl2br()}
        </div>
    #{/if}
</div>