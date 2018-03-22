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
import mage.deck.Modern;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 *
 * @author LevelX2
 */
public class ModernDeckValidatorTest extends DeckValidatorTest {

    @Test
    public void testModern1() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();

        deck.add(new CardNameAmount("Counterspell", 5));
        deck.add(new CardNameAmount("Mountain", 56));

        Assert.assertFalse("only 4 of a card are allowed", testDeckValid(new Modern(), deck));
    }

    @Test
    public void testModernCounterspell1() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();
        deck.add(new CardNameAmount("DD3JVC", 24, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("6ED", 61, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("5ED", 77, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("4ED", 65, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("JR", 5, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("DD2", 24, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("ICE", 64, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("FNMP", 66, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("LEA", 55, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("LEB", 55, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("ME4", 45, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("ME2", 44, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("S99", 34, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("7ED", 67, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("3ED", 54, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("MMQ", 69, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("VMA", 64, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("2ED", 55, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("TPR", 43, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("TMP", 57, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

        deck.clear();
        deck.add(new CardNameAmount("S00", 12, 4));
        deck.add(new CardNameAmount("Mountain", 56));
        Assert.assertFalse("Counterspell not allowed in modern", testDeckValid(new Modern(), deck));

    }

    @Test
    public void testModernBanned() {
        ArrayList<CardNameAmount> deck = new ArrayList<>();
        DeckValidator validator = new Modern();

        deck.add(new CardNameAmount("Ancestral Vision", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        boolean validationSuccessful = testDeckValid(validator, deck);
        Assert.assertTrue(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Ancient Den", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.add(new CardNameAmount("Birthing Pod", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Blazing Shoal", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Bloodbraid Elf", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertTrue(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Chrome Mox", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Cloudpost", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Dark Depths", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Deathrite Shaman", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Dig Through Time", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Dread Return", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Glimpse of Nature", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Great Furnace", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Green Sun's Zenith", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Hypergenesis", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Jace, the Mind Sculptor", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertTrue(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();

        deck.clear();
        deck.add(new CardNameAmount("Mental Misstep", 4));
        deck.add(new CardNameAmount("Mountain", 56));
        validationSuccessful = testDeckValid(validator, deck);
        Assert.assertFalse(validator.getInvalid().toString(), validationSuccessful);
        validator.getInvalid().clear();
    }
}
