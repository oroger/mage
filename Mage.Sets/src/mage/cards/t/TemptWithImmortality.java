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
package mage.cards.t;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.other.OwnerIdPredicate;
import mage.game.Game;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInYourGraveyard;

/**
 *
 * @author LevelX2
 */
public class TemptWithImmortality extends CardImpl {

    public TemptWithImmortality(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{4}{B}");

        // Tempting offer - Return a creature card from your graveyard to the battlefield. Each opponent may return a creature card from his or her graveyard to the battlefield. For each player who does, return a creature card from your graveyard to the battlefield.
        this.getSpellAbility().addEffect(new TemptWithImmortalityEffect());
    }

    public TemptWithImmortality(final TemptWithImmortality card) {
        super(card);
    }

    @Override
    public TemptWithImmortality copy() {
        return new TemptWithImmortality(this);
    }
}

class TemptWithImmortalityEffect extends OneShotEffect {

    public TemptWithImmortalityEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "<i>Tempting offer</i> - Return a creature card from your graveyard to the battlefield. Each opponent may return a creature card from his or her graveyard to the battlefield. For each player who does, return a creature card from your graveyard to the battlefield";

    }

    public TemptWithImmortalityEffect(final TemptWithImmortalityEffect effect) {
        super(effect);
    }

    @Override
    public TemptWithImmortalityEffect copy() {
        return new TemptWithImmortalityEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            returnCreatureFromGraveToBattlefield(controller, source, game);

            int opponentsReturnedCreatures = 0;
            for (UUID playerId : game.getOpponents(controller.getId())) {
                Player opponent = game.getPlayer(playerId);
                if (opponent != null) {
                    FilterCard filter = new FilterCreatureCard("creature card from your graveyard");
                    filter.add(new OwnerIdPredicate(opponent.getId()));
                    Target targetCardOpponent = new TargetCardInGraveyard(filter);

                    if (targetCardOpponent.canChoose(source.getSourceId(), opponent.getId(), game)) {
                        if (opponent.chooseUse(outcome, "Return a creature card from your graveyard to the battlefield?", source, game)) {
                            if (opponent.chooseTarget(outcome, targetCardOpponent, source, game)) {
                                Card card = game.getCard(targetCardOpponent.getFirstTarget());
                                if (card != null) {
                                    opponentsReturnedCreatures++;
                                    opponent.moveCards(card, Zone.BATTLEFIELD, source, game);
                                }
                            }
                        }
                    }
                }
            }
            if (opponentsReturnedCreatures > 0) {
                for (int i = 0; i < opponentsReturnedCreatures; i++) {
                    returnCreatureFromGraveToBattlefield(controller, source, game);
                }
            }
            return true;
        }

        return false;
    }

    private boolean returnCreatureFromGraveToBattlefield(Player player, Ability source, Game game) {
        Target target = new TargetCardInYourGraveyard(new FilterCreatureCard());
        target.setNotTarget(false);
        if (target.canChoose(source.getSourceId(), source.getControllerId(), game)) {
            if (player.chooseTarget(outcome, target, source, game)) {
                Card card = game.getCard(target.getFirstTarget());
                if (card != null) {
                    return player.moveCards(card, Zone.BATTLEFIELD, source, game);
                }
            }
        }
        return false;
    }
}
