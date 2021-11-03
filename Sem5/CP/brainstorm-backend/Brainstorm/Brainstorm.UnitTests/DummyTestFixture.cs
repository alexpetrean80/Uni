using NUnit.Framework;

namespace Brainstorm.Testing {
    [TestFixture]
    public class DummyTestFixture {
        [Test]
        public void Pass() {
            Assert.True(true);
        }
    }
}