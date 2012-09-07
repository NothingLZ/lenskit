/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2012 Regents of the University of Minnesota and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.grouplens.lenskit.core;

import org.grouplens.grapht.Binding;
import org.grouplens.grapht.Context;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

/**
 * Wrap a Grapht context to make a LensKit context.
 *
 * @see 1.0
 * @author Michael Ekstrand
 */
public class ContextWrapper extends AbstractConfigContext {
    private Context base;

    public ContextWrapper(Context ctx) {
        base = ctx;
    }

    /**
     * Coerce a Grapht context to a LensKit context.
     * @param ctx The context.
     * @return A LensKit context, as a wrapper if necessary.
     */
    static LenskitConfigContext coerce(Context ctx) {
        if (ctx instanceof LenskitConfigContext) {
            return (LenskitConfigContext) ctx;
        } else {
            return new ContextWrapper(ctx);
        }
    }

    @Override
    public <T> Binding<T> bind(Class<T> type) {
        return base.bind(type);
    }

    @Override
    public <T> Binding<T> bind(Class <? extends Annotation> qualifier, Class<T> type) {
        return base.bind(type).withQualifier(qualifier);
    }

    @Override
    public LenskitConfigContext in(Class<?> type) {
        return coerce(base.in(type));
    }

    @Override
    public LenskitConfigContext in(@Nullable Class<? extends Annotation> qualifier, Class<?> type) {
        return coerce(base.in(qualifier, type));
    }

    @Override
    public LenskitConfigContext in(@Nullable Annotation qualifier, Class<?> type) {
        return coerce(base.in(qualifier, type));
    }
}
