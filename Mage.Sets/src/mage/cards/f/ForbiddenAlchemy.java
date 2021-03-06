/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.f;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.*;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.TimingRule;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetCard;

/**
 *
 * @author North
 */
public class ForbiddenAlchemy extends CardImpl {

    public ForbiddenAlchemy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{2}{U}");


        // Look at the top four cards of your library. Put one of them into your hand and the rest into your graveyard.
        this.getSpellAbility().addEffect(new ForbiddenAlchemyEffect());
        // Flashback {6}{B}
        this.addAbility(new FlashbackAbility(new ManaCostsImpl("{6}{B}"), TimingRule.INSTANT));
    }

    public ForbiddenAlchemy(final ForbiddenAlchemy card) {
        super(card);
    }

    @Override
    public ForbiddenAlchemy copy() {
        return new ForbiddenAlchemy(this);
    }
}

class ForbiddenAlchemyEffect extends OneShotEffect {

    public ForbiddenAlchemyEffect() {
        super(Outcome.DrawCard);
        this.staticText = "Look at the top four cards of your library. Put one of them into your hand and the rest into your graveyard";
    }

    public ForbiddenAlchemyEffect(final ForbiddenAlchemyEffect effect) {
        super(effect);
    }

    @Override
    public ForbiddenAlchemyEffect copy() {
        return new ForbiddenAlchemyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());

        if (player != null) {
            Cards cards = new CardsImpl();
            int cardsCount = Math.min(4, player.getLibrary().size());
            for (int i = 0; i < cardsCount; i++) {
                Card card = player.getLibrary().removeFromTop(game);
                if (card != null) {
                    cards.add(card);
                }
            }

            if (!cards.isEmpty()) {
                player.lookAtCards("Forbidden Alchemy", cards, game);

                TargetCard target = new TargetCard(Zone.LIBRARY, new FilterCard("card to put in your hand"));
                if (player.choose(Outcome.Benefit, cards, target, game)) {
                    Card card = cards.get(target.getFirstTarget(), game);
                    if (card != null) {
                        card.moveToZone(Zone.HAND, source.getSourceId(), game, false);
                        cards.remove(card);
                    }
                }

                for (Card card : cards.getCards(game)) {
                    card.moveToZone(Zone.GRAVEYARD, source.getSourceId(), game, true);
                }
            }
            return true;
        }
        return false;
    }
}
