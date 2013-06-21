package ch.cyberduck.core.cf;

/*
 * Copyright (c) 2013 David Kocher. All rights reserved.
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

import ch.cyberduck.core.exception.DefaultIOExceptionMappingService;
import ch.cyberduck.core.exception.FilesExceptionMappingService;
import ch.cyberduck.core.threading.BackgroundException;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rackspacecloud.client.cloudfiles.FilesClient;
import com.rackspacecloud.client.cloudfiles.FilesContainer;
import com.rackspacecloud.client.cloudfiles.FilesException;
import com.rackspacecloud.client.cloudfiles.FilesRegion;

/**
 * @version $Id$
 */
public class SwiftContainerListService {
    private static final Logger log = Logger.getLogger(SwiftContainerListService.class);

    public List<FilesContainer> list(final CFSession session) throws BackgroundException {
        if(log.isDebugEnabled()) {
            log.debug(String.format("List containers for %s", session));
        }
        try {
            final List<FilesContainer> containers = new ArrayList<FilesContainer>();
            final FilesClient client = session.getClient();
            for(FilesRegion region : client.getRegions()) {
                // List all containers
                for(FilesContainer container : client.listContainers(region)) {
                    containers.add(container);
                }
            }
            return containers;
        }
        catch(FilesException e) {
            throw new FilesExceptionMappingService().map("Listing directory failed", e);
        }
        catch(IOException e) {
            throw new DefaultIOExceptionMappingService().map(e);
        }
    }
}
