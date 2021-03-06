/*
 * Copyright (C) 2017 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.julienviet.pgclient.impl;

import com.julienviet.pgclient.impl.codec.Column;
import com.julienviet.pgclient.impl.codec.DataFormat;
import com.julienviet.pgclient.impl.codec.decoder.message.ParameterDescription;
import com.julienviet.pgclient.impl.codec.decoder.message.RowDescription;

import java.util.Arrays;

public class PreparedStatement {

  final String sql;
  final long statement;
  final ParameterDescription paramDesc;
  final RowDescription rowDesc;

  public PreparedStatement(String sql, long statement, ParameterDescription paramDesc, RowDescription rowDesc) {

    // Fix to use binary
    if (rowDesc != null) {
      rowDesc = new RowDescription(Arrays.stream(rowDesc.columns())
        .map(c -> new Column(c.getName(), c.getRelationId(), c.getRelationAttributeNo(), c.getDataType(), c.getLength(), c.getTypeModifier(), DataFormat.BINARY))
        .toArray(Column[]::new));
    }

    this.sql = sql;
    this.statement = statement;
    this.paramDesc = paramDesc;
    this.rowDesc = rowDesc;
  }
}
