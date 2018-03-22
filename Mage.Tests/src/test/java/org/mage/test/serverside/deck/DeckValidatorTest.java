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

import mage.cards.decks.Deck;
import mage.cards.decks.DeckValidator;
import mage.cards.repository.CardInfo;
import mage.cards.repository.CardRepository;
import org.mage.test.serverside.base.MageTestBase;

import java.util.List;

/**
 *
 * @author LevelX2
 */
public abstract class DeckValidatorTest extends MageTestBase {

    static class CardNameAmount {

        String name;
        String setCode;
        String cardNumber;

        int number;

        public CardNameAmount(String setCode, int cardNumber, int number) {
            this.name = "";
            this.setCode = setCode;
            this.cardNumber = String.valueOf(cardNumber);
            this.number = number;
        }

        public CardNameAmount(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

        public String getSetCode() {
            return setCode;
        }

        public String getCardNumber() {
            return cardNumber;
        }

    }

    protected boolean testDeckValid(DeckValidator validator, List<CardNameAmount> cards) {
        return testDeckValid(validator, cards, null);
    }

    protected boolean testDeckValid(DeckValidator validator, List<CardNameAmount> cards, List<CardNameAmount> cardsSideboard) {
        Deck deckToTest = new Deck();
        if (cards != null) {
            for (CardNameAmount cardNameAmount : cards) {
                CardInfo cardinfo;
                if (cardNameAmount.getName().isEmpty()) {
                    cardinfo = CardRepository.instance.findCard(cardNameAmount.getSetCode(), cardNameAmount.getCardNumber());
                } else {
                    cardinfo = CardRepository.instance.findCard(cardNameAmount.getName());
                }
                for (int i = 0; i < cardNameAmount.getNumber(); i++) {
                    deckToTest.getCards().add(cardinfo.getCard());
                }
            }
        }
        if (cardsSideboard != null) {
            for (CardNameAmount cardNameAmount : cardsSideboard) {
                CardInfo cardinfo;
                if (cardNameAmount.getName().isEmpty()) {
                    cardinfo = CardRepository.instance.findCard(cardNameAmount.getSetCode(), cardNameAmount.getCardNumber());
                } else {
                    cardinfo = CardRepository.instance.findCard(cardNameAmount.getName());
                }
                for (int i = 0; i < cardNameAmount.getNumber(); i++) {
                    deckToTest.getSideboard().add(cardinfo.getCard());
                }
            }
        }
        return validator.validate(deckToTest);
    }
}
