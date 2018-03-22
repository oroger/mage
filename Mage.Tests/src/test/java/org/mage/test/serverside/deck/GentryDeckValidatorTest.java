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
package org.mage.test.serverside.deck;

import mage.cards.decks.DeckValidator;
import mage.deck.Gentry;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 *
 * @author oroger
 */
public class GentryDeckValidatorTest extends DeckValidatorTest {

    @Test
    public void testValid() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();

        deck.add(new CardNameAmount("Cancel", 4));
        deck.add(new CardNameAmount("Baffling End", 4));
        deck.add(new CardNameAmount("Blazing Hope", 4));
        deck.add(new CardNameAmount("Brass's Bounty", 1));
        deck.add(new CardNameAmount("Mountain", 47));

        DeckValidator validator = new Gentry();
        boolean validationSuccessful = testDeckValid(validator, deck);
        Assert.assertTrue(validator.getInvalid().toString(), validationSuccessful);
    }

    @Test
    public void testSideboardTooLarge() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();
        ArrayList<CardNameAmount> sideboard = new ArrayList<>();

        deck.add(new CardNameAmount("Brass's Bounty", 1));
        deck.add(new CardNameAmount("Mountain", 59));

        sideboard.add(new CardNameAmount("Mountain", 58));

        DeckValidator validator = new Gentry();
        boolean validation = testDeckValid(validator, deck, sideboard);
        Assert.assertFalse(validator.getInvalid().toString(), validation);

        Assert.assertEquals("invalid message not correct",
                "{Sideboard=Must contain no more than 15 cards : has 58 cards}", validator.getInvalid().toString());
    }

    @Test
    public void testSameRareCardInDeckAndSideboard() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();
        ArrayList<CardNameAmount> sideboard = new ArrayList<>();

        deck.add(new CardNameAmount("Brass's Bounty", 1));
        deck.add(new CardNameAmount("Mountain", 59));

        sideboard.add(new CardNameAmount("Brass's Bounty", 1));

        DeckValidator validator = new Gentry();
        boolean validation = testDeckValid(validator, deck, sideboard);
        Assert.assertFalse(validator.getInvalid().toString(), validation);

        Assert.assertEquals("invalid message not correct",
                "{Brass's Bounty=Too many: 2. Rare/mythic card have to be individually different.}", validator.getInvalid().toString());
    }

    @Test
    public void testTooManyUncommons() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();

        deck.add(new CardNameAmount("Charging Tuskodon", 4));
        deck.add(new CardNameAmount("Cherished Hatchling", 4));
        deck.add(new CardNameAmount("Crested Herdcaller", 4));
        deck.add(new CardNameAmount("Curious Obsession", 4));
        deck.add(new CardNameAmount("Mountain", 44));

        DeckValidator validator = new Gentry();
        boolean validation = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validation);

        Assert.assertEquals("invalid message not correct",
                "{Uncommons=Deck can contain up to 15 uncommon cards: has 16 cards}", validator.getInvalid().toString());
    }

    @Test
    public void testTooManyRares() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();

        deck.add(new CardNameAmount("Brass's Bounty", 1));
        deck.add(new CardNameAmount("Blood Sun", 1));
        deck.add(new CardNameAmount("Deeproot Elite", 1));
        deck.add(new CardNameAmount("Dire Fleet Poisoner", 1));
        deck.add(new CardNameAmount("Etali, Primal Storm", 1));
        deck.add(new CardNameAmount("Mountain", 55));

        DeckValidator validator = new Gentry();
        boolean validation = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validation);

        Assert.assertEquals("invalid message not correct",
                "{Rares & Mythics=Deck can contain up to 4 rare/mythic cards: has 5 cards}", validator.getInvalid().toString());
    }

    @Test
    public void testTooManyIdenticalRares() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();

        deck.add(new CardNameAmount("Brass's Bounty", 2));
        deck.add(new CardNameAmount("Mountain", 58));

        DeckValidator validator = new Gentry();
        boolean validation = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validation);

        Assert.assertEquals("invalid message not correct",
                "{Brass's Bounty=Too many: 2. Rare/mythic card have to be individually different.}", validator.getInvalid().toString());
    }

}
