public class Polynomial
{
    public List<int>? Coefficients { get; init; }

    public override string ToString()
    {
        var res = "";
        if (Coefficients is null || Coefficients.Count == 0)
        {
            return res;
        }

        for (var i = Coefficients.Count - 1; i > 0; i--)
        {
            if (Coefficients[i] == 0)
            {
                continue;
            }
            res += $"({Coefficients[i]}x^{i}) + ";
        }

        if (Coefficients[0] != 0)
        {
            res += $"({Coefficients[0]})";
        }
        return res;
    }

    public static Polynomial operator +(Polynomial self, Polynomial other)
    {
        var res = new Polynomial { Coefficients = new() };

        for (var i = 0; i < self.Coefficients!.Count; i++)
        {
            res.Coefficients.Add(self.Coefficients![i] + (other.Coefficients!.Count > i ? other.Coefficients![i] : 0));
        }

        return res;
    }

    public static Polynomial operator -(Polynomial self, Polynomial other)
    {
        var res = new Polynomial { Coefficients = new() };

        for (var i = 0; i < self.Coefficients!.Count; i++)
        {
            res.Coefficients.Add(self.Coefficients![i] - (other.Coefficients!.Count > i ? other.Coefficients![i] : 0));
        }

        return res;
    }

    public static Polynomial operator *(Polynomial self, int coefficient)
    {
        var res = new Polynomial { Coefficients = new() };
        while (coefficient > 0)
        {
            res.Coefficients.Add(0);
            coefficient--;
        }

        self.Coefficients!.ForEach((c) => res.Coefficients.Add(c));
        return res;
    }

    public Tuple<Polynomial, Polynomial> GetHalve()
    {
        var second = new Polynomial
        {
            Coefficients = this.Coefficients!
                                     .Take(this.Coefficients!.Count / 2)
                                     .ToList()
        };

        var first = new Polynomial
        {
            Coefficients = this.Coefficients!.Skip(this.Coefficients!.Count / 2).Take(this.Coefficients!.Count - this.Coefficients!.Count / 2).ToList()
        };

        return Tuple.Create(first, second);
    }
}
