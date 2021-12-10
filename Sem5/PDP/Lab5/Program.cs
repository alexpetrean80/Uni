TimeSpan Count(Action mul)
{
    var start = DateTime.Now;
    mul();
    return DateTime.Now - start;
}

Polynomial MulRegSeq(Polynomial poly1, Polynomial poly2)
{
    var res = new Polynomial { Coefficients = new() };

    for (int i = 0; i < poly1.Coefficients!.Count; i++)
    {
        for (int i1 = 0; i1 < poly2.Coefficients!.Count; i1++)
        {
            var ir = i + i1;
            var cr = poly1.Coefficients[i] * poly2.Coefficients[i1];
            while (res.Coefficients.Count <= ir)
            {
                res.Coefficients.Add(0);
            }
            res.Coefficients[ir] += cr;
        }
    }

    return res;
}

Polynomial MulRegPar(Polynomial poly1, Polynomial poly2)
{
    var res = new Polynomial { Coefficients = new() };
    var mtx = new Mutex();

    Parallel.For(0, poly1.Coefficients!.Count, (int i) =>
    {
        Parallel.For(0, poly2.Coefficients!.Count, (int i1) =>
        {
            int ir = i;
            Interlocked.Add(ref ir, i1);

            int cr = 0;
            Interlocked.Exchange(ref cr, poly1.Coefficients[i] * poly2.Coefficients[i1]);


            mtx.WaitOne();
            while (res.Coefficients.Count <= ir)
            {
                res.Coefficients.Add(0);
            }
            mtx.ReleaseMutex();

            mtx.WaitOne();
            res.Coefficients[ir] += cr;
            mtx.ReleaseMutex();
        });
    });

    mtx.Dispose();

    return res;
}

Polynomial MulKarSeq(Polynomial poly1, Polynomial poly2)
{
    var p1h = poly1.GetHalve();
    var p2h = poly2.GetHalve();

    var prf = MulRegSeq(p1h.Item1, p2h.Item1);
    var prs = MulRegSeq(p1h.Item2, p2h.Item2);

    var p1s = p1h.Item1 + p1h.Item2;

    var p2s = p2h.Item1 + p2h.Item2;

    var ps = MulRegSeq(p1s, p2s) - prf - prs;

    var res = prf * (poly1.Coefficients!.Count - 1) + ps * ((poly1.Coefficients.Count - 1) / 2) + prs;

    return res;
}

Polynomial MulKarPar(Polynomial poly1, Polynomial poly2)
{
    var p1h = Tuple.Create(new Polynomial(), new Polynomial());
    var p2h = Tuple.Create(new Polynomial(), new Polynomial());

    var p1ht = new Task(() => p1h = poly1.GetHalve());
    var p2ht = new Task(() => p2h = poly2.GetHalve());

    p1ht.Start();
    p2ht.Start();

    Task.WaitAll(new Task[] { p1ht, p2ht });


    var prf = new Polynomial();
    var prft = new Task(() => prf = MulRegPar(p1h.Item1, p2h.Item1));
    prft.Start();

    var prs = new Polynomial();
    var prst = new Task(() => prs = MulRegPar(p1h.Item2, p2h.Item2));
    prst.Start();

    var p1s = new Polynomial();
    var p1st = new Task(() => p1s = p1h.Item1 + p1h.Item2);
    p1st.Start();

    var p2s = new Polynomial();
    var p2st = new Task(() => p2s = p2h.Item1 + p2h.Item2);
    p2st.Start();

    Task.WaitAll(new Task[] { prft, prst, p1st, p2st });


    var ps = MulRegPar(p1s, p2s) - prf - prs;

    var res = prf * (poly1.Coefficients!.Count - 1) + ps * ((poly1.Coefficients.Count - 1) / 2) + prs;

    return res;

}

List<int> GetRandom(int count)
{
    var rnd = new Random();
    var res = new List<int>();
    Parallel.For(0, count, (_) => res.Add(rnd.Next()));
    return res;
}
var poly1 = new Polynomial
{
    Coefficients = new() { 3, 2, 1 }
};

var poly2 = new Polynomial { Coefficients = new() { 7, 6, 5 } };

Console.WriteLine($"poly1={poly1.ToString()}");
Console.WriteLine($"poly2={poly2.ToString()}");

var resRegSeq = new Polynomial();
var time1 = Count(() =>
{
    resRegSeq = MulRegSeq(poly1, poly2);
});
Console.WriteLine("\nRegular Sequential:");
Console.WriteLine($"polyRegSeq={resRegSeq.ToString()}");
Console.WriteLine($"timeRegSeq={time1}");

var resRegPar = new Polynomial();
var time2 = Count(() =>
{
    resRegPar = MulRegPar(poly1, poly2);
});
Console.WriteLine($"\nRegular Parallel:");
Console.WriteLine($"polyRegPar={resRegPar.ToString()}");
Console.WriteLine($"timeRegPar={time2}");

var resKarSeq = new Polynomial();
var time3 = Count(() =>
{
    resKarSeq = MulKarSeq(poly1, poly2);
});
Console.WriteLine($"\nKaratsuba Sequential:");
Console.WriteLine($"polyKarSeq={resKarSeq.ToString()}");
Console.WriteLine($"timeRegPar={time3}");

var resKarPar = new Polynomial();
var time4 = Count(() =>
{
    resKarPar = MulKarPar(poly1, poly2);
});
Console.WriteLine($"\nKaratsuba Parallel:");
Console.WriteLine($"polyKarPar={resKarPar.ToString()}");
Console.WriteLine($"timeRegPar={time4}");
