/*
 * Copyright 2011 BetaSteward_at_googlemail.com. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of BetaSteward_at_googlemail.com.
 */
package mage.deck;

import mage.cards.Card;
import mage.cards.ExpansionSet;
import mage.cards.Sets;
import mage.cards.decks.Constructed;
import mage.cards.decks.Deck;
import mage.constants.Rarity;
import mage.constants.SetType;

import java.util.*;

/**
 *
 * @author oroger
 * @see <a href="http://gentrymagic.com/about/">gentrymagic.com</a>``
 */
public class Gentry extends Constructed {

    public Gentry() {
        this("Gentry");
    }

    public Gentry(String name) {
        super(name);
        GregorianCalendar current = new GregorianCalendar();
        List<ExpansionSet> sets = new ArrayList(Sets.getInstance().values());
        Collections.sort(sets, new Comparator<ExpansionSet>() {
            @Override
            public int compare(final ExpansionSet lhs, ExpansionSet rhs) {
                return lhs.getReleaseDate().after(rhs.getReleaseDate()) ? -1 : 1;
            }
        });
        int blocksAdded = 0;
        int blocksToAdd = 3;
        for (Iterator<ExpansionSet> iter = sets.iterator(); iter.hasNext() && blocksAdded < blocksToAdd;) {
            ExpansionSet set = iter.next();
            if (set.getSetType() == SetType.CORE || set.getSetType() == SetType.EXPANSION || set.getSetType() == SetType.SUPPLEMENTAL_STANDARD_LEGAL) {    // Still adding core sets because of Magic Origins

                setCodes.add(set.getCode());
                if (set.getReleaseDate().before(current.getTime()) // This stops spoiled sets from counting as "new" blocks
                        && set.getParentSet() == null
                        && set.getSetType() == SetType.EXPANSION) {
                    if (blocksAdded == 0 && !isFallBlock(set)) { // if the most current block is no fall block, 4 blocks are added
                        blocksToAdd++;
                    }
                    blocksAdded++;
                }
            }
        }
        banned.add("Attune with Aether"); // since 2008-01-15
        banned.add("Aetherworks Marvel");
        banned.add("Felidar Guardian");
        banned.add("Rampaging Ferocidon"); // since 2008-01-15
        banned.add("Ramunap Ruins"); // since 2008-01-15
        banned.add("Rogue Refiner"); // since 2008-01-15
        banned.add("Smuggler's Copter");
    }

    private static boolean isFallBlock(ExpansionSet set) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(set.getReleaseDate());
        // Sets from fall block are normally released in September and January
        return cal.get(Calendar.MONTH) > 7 || cal.get(Calendar.MONTH) < 2;
    }

    @Override
    public boolean validate(Deck deck) {
        boolean valid = super.validate(deck);

        /**
         * 1. You are only allowed cards that appear in the last 3 block. These are the Standard legal sets. (From Standard Constructor)
         * 2. Your main deck has to have at least 60 cards, your sideboard can have up to 15 cards. (From Constructed.validate())
         * 3. You are allowed a maximum of 15 Uncommons
         * 4. You are allowed a maximum of 4 Rares and/or Mythic rares in your combined maindeck and sideboard that have to be individually different.
         */

        List<Card> uncommons = getCardsFromRarity(deck, Rarity.UNCOMMON);
        if (uncommons.size() > 15) {
            invalid.put("Uncommons", "Can contain up to 15 uncommon cards: has " + uncommons.size() + " cards");
            valid = false;
        }

        List<Card> raresAndMythics = getCardsFromRarity(deck, Rarity.RARE, Rarity.MYTHIC);
        if (raresAndMythics.size() > 4) {
            invalid.put("Rares & Mythics", "Can contain up to 4 rare/mythic cards: has " + raresAndMythics.size() + " cards");
            valid = false;
        }

        return valid;
    }

    private List<Card> getCardsFromRarity(Deck deck, Rarity... rarity) {
        List<Rarity> rarityLevels = Arrays.asList(rarity);
        List<Card> matching = new ArrayList<>();

        List<Card> cards = new ArrayList<>();
        cards.addAll(deck.getCards());
        cards.addAll(deck.getSideboard());

        for (Card card : cards) {
            if (rarityLevels.contains(card.getRarity())) {
                matching.add(card);
            }
        }

        return matching;
    }

}
