package com.phyder.myhelper;

import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * Created on 24/03/17.
 *
 * @author Suraj Singh
 *         Phyder Solutions Private Limited, a division of, Cyber Manager Software Services Private Limited
 */

public final class TextInputLayoutMatchers {

    private static class ErrorMatcher extends BoundedMatcher<Object, TextInputLayout> {

        private final Matcher<String> itemTextMatcher;

        public ErrorMatcher(final Matcher<String> itemTextMatcher) {
            super(TextInputLayout.class);
            this.itemTextMatcher = itemTextMatcher;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with error  content: ");
            itemTextMatcher.describeTo(description);
        }

        @Override
        protected boolean matchesSafely(TextInputLayout til) {
            if (til == null) {
                return false;
            }
            return itemTextMatcher.matches((til.getError()));
        }
    }

    public static Matcher<Object> withErrorText(final String errorMessage) {
        checkNotNull(errorMessage);
        return withErrorText(Matchers.is(errorMessage));
    }

    public static Matcher<Object> withErrorText(final Matcher<String> itemTextMatcher) {
        return new ErrorMatcher(itemTextMatcher);
    }
}
