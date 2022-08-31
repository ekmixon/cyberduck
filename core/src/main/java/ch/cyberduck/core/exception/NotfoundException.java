package ch.cyberduck.core.exception;

/*
 * Copyright (c) 2002-2013 David Kocher. All rights reserved.
 * http://cyberduck.ch/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Bug fixes, suggestions and comments should be sent to:
 * feedback@cyberduck.ch
 */

import ch.cyberduck.core.LocaleFactory;

public class NotfoundException extends BackgroundException {
    private static final long serialVersionUID = -5634899621865307418L;

    public NotfoundException(final String detail) {
        super("File not found", detail);
    }

    public NotfoundException(final String detail, final Throwable cause) {
        super("File not found", detail, cause);
    }

    @Override
    public String getMessage() {
        return null == super.getMessage() ? LocaleFactory.localizedString("Unknown") :
                LocaleFactory.localizedString(super.getMessage(), "Error");
    }

    @Override
    public String getHelp() {
        return LocaleFactory.localizedString("Please contact your web hosting service provider for assistance", "Support");
    }
}
