<div class="post ${_as == 'teaser' ? 'teaser' : ''}">
    <div class="post-metadata">
        <span class="post-author">by
        	#{list items:_post.authors, as:'author'}
        	  <a href="@{Application.phrasesfrom(author)}">${author.name}</a>(${author.score})
        	#{/list}
        </span>
        <span class="post-date">${_post.postedAt.format('dd MMM yy')} <a href="@{Application.phrase(_post.id)}">link</a></span>
    </div>
    #{if _as != 'teaser'}
    <div class="post-panel">
        
        <div class="post-score"><a href="@{Votes.voteFor(_post.id)}">+1</a></div>
        <div class="post-score">${_post.score}</div>
        <div class="post-content">
            ${_post.phrase.nl2br()}
        </div>
    </div>
    #{/if}
</div>