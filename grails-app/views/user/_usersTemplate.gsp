<div>
    <g:if test="${users}">

        <g:each var="user" in="${users}">
            <p>User with name: ${user.name}, has next pokemon(s):</p>
            <g:each var="pokemon" in="${user.pokemons}">
                <p>Pokemon name: ${pokemon.name}</p>
            </g:each>
        </g:each>

        <div class="paginate">
            <g:paginate offset="${offset}" max="${max}" total="${userCount}"/>
        </div>
    </g:if>
</div>
