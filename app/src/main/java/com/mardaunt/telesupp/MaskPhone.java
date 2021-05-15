package com.mardaunt.telesupp;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class MaskPhone {

    public static FormatWatcher watcher;

    public static void setUpMaskRu(EditText inputForm) {
        MaskImpl mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER);
        if (watcher != null && watcher.hasMask()) watcher.removeFromTextView();
        watcher = new MaskFormatWatcher(mask);
        watcher.installOn(inputForm);
    }

    public static void setUpMaskAll(EditText inputForm) {
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("+________________");
        if (watcher != null && watcher.hasMask()) watcher.removeFromTextView();
        watcher = new MaskFormatWatcher(MaskImpl.createTerminated(slots));
        watcher.installOn(inputForm);
    }
}
