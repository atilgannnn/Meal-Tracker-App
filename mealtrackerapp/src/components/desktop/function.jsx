const [inputValue, setInputValue] = useState('');
const [items, setItems] = useState([]);

const handleChange = (event) => {
  setInputValue(event.target.value);
};

const handleSubmit = (event) => {
  event.preventDefault();
  if (!inputValue.trim()) return; // Prevent adding empty items

  // Create a new array with the current items plus the new one
  const updatedItems = [...items, inputValue];

  // Update the state with the new array of items and reset the input value
  setItems(updatedItems);
  setInputValue('');
};

return (
  <div>
    <h1>React List Example</h1>
    <form onSubmit={handleSubmit}>
      <input
        type='text'
        value={inputValue}
        onChange={handleChange}
        placeholder='Enter item'
      />
      <button type='submit'>Add Item</button>
    </form>
    <ul>
      {/* Map through the items array and render each item as a list item */}
      {items.map((item, index) => (
        <li key={index}>{item}</li>
      ))}
    </ul>
  </div>
);
