using System;
using System.Collections.Generic;
using System.Linq;

namespace Lib
{
    public class Matrix
    {
        private readonly List<List<int>> _matrix;

        public Matrix(List<List<int>> m)
        {
            this._matrix = m;
        }

        public List<int> GetLine(int index)
        {
            if (index >= _matrix.Count)
            {
                throw new Exception("Index is to big");
            }

            return _matrix[index];
        }

        public List<int> GetColumn(int index)
        {
            if (index >= _matrix[0].Count)
            {
                throw new Exception("Index is to big");
            }

            return _matrix
                .Select(l => l[index])
                .ToList();
        }
    }
}